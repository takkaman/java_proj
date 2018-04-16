package org.lenskit.mooc.uu;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.longs.Long2DoubleMap;
import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2DoubleSortedMap;
import it.unimi.dsi.fastutil.longs.LongSet;
import org.apache.commons.math3.analysis.function.Abs;
import org.lenskit.api.Result;
import org.lenskit.api.ResultMap;
import org.lenskit.basic.AbstractItemScorer;
import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.entities.CommonAttributes;
import org.lenskit.data.entities.CommonTypes;
import org.lenskit.data.ratings.Rating;
import org.lenskit.results.Results;
import org.lenskit.util.ScoredIdAccumulator;
import org.lenskit.util.TopNScoredIdAccumulator;
import org.lenskit.util.collections.LongUtils;
import org.lenskit.util.math.Scalars;
import org.lenskit.util.math.Vectors;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.*;

/**
 * User-user item scorer.
 * @author <a href="http://www.grouplens.org">GroupLens Research</a>
 */
public class SimpleUserUserItemScorer extends AbstractItemScorer {
    private final DataAccessObject dao;
    private final int neighborhoodSize;

    /**
     * Instantiate a new user-user item scorer.
     * @param dao The data access object.
     */
    @Inject
    public SimpleUserUserItemScorer(DataAccessObject dao) {
        this.dao = dao;
        neighborhoodSize = 30;
    }

    @Nonnull
    @Override
    public ResultMap scoreWithDetails(long user, @Nonnull Collection<Long> items) {
        // TODO Score the items for the user with user-user CF
        List<Result> results = new ArrayList<>();
        LongSet userListAll = dao.getEntityIds(CommonTypes.USER);
        // LongSet userList = null;
        List<Result> similarities = new ArrayList<>();
        for (Long usr: userListAll) {

            // System.out.println(cosineUV(user, usr));
            similarities.add(Results.create(usr, cosineUV(user, usr)));

        }
        Collections.sort(similarities, new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                if (o1.getScore() < o2.getScore()) return 1;
                return -1;
            }
        });

        Long2DoubleOpenHashMap vecU = getUserRatingVector(user);
        Double avgScoreU = Vectors.mean(vecU);

        for (Long item: items) {
            int itemCnt = 1;
            double sumA = 0, sumB = 0;
            //System.out.println(item);
            for (Result rs : similarities) {
                Long usr = rs.getId();
                // System.out.println(rs.getScore());
                if (itemCnt > neighborhoodSize) break;
                Long2DoubleOpenHashMap vecV = getUserRatingVector(usr);
                Double cosUV = rs.getScore();
                Double avgScoreV = Vectors.mean(vecV);
                if (vecV.containsKey(item) && cosUV > 0) {
                    itemCnt++;
                    Double scoreVI = vecV.get(item);
                    sumB += Math.abs(cosUV);
                    sumA += cosUV * (scoreVI - avgScoreV);
                }
            }
            if (itemCnt >= 2) {
                Double predictUI = avgScoreU + sumA / sumB;
                //System.out.println(predictUI);
                Result r = Results.create(item, predictUI);
//                System.out.println(r.getScore());
                results.add(r);
            }
        }

        return Results.newResultMap(results);
    }

    public Double getAvgScore(long user) {
        double avgScore = 0;
        Long2DoubleOpenHashMap selfRatings = getUserRatingVector(user);
        for (Long key: selfRatings.keySet()) {
            avgScore += selfRatings.get(key);
        }
        avgScore = avgScore / Double.valueOf(selfRatings.size());
        return avgScore;
    }

    public Double cosineUV(long userU, long userV) {
        double result;
        Long2DoubleOpenHashMap vecU = getUserRatingVector(userU);
        Long2DoubleOpenHashMap vecV = getUserRatingVector(userV);
        Double avgScoreU = Vectors.mean(vecU);
        Double avgScoreV = Vectors.mean(vecV);
        for (Long key: vecU.keySet()) {
            vecU.put(key, Double.valueOf(vecU.get(key)-avgScoreU));
        }
        for (Long key: vecV.keySet()) {
            vecV.put(key, Double.valueOf(vecV.get(key)-avgScoreV));
        }
        result = Vectors.dotProduct(vecU, vecV) / (Vectors.euclideanNorm(vecU) * Vectors.euclideanNorm(vecV));
        return result;
    }

    /**
     * Get a user's rating vector.
     * @param user The user ID.
     * @return The rating vector, mapping item IDs to the user's rating
     *         for that item.
     */
    private Long2DoubleOpenHashMap getUserRatingVector(long user) {
        List<Rating> history = dao.query(Rating.class)
                                  .withAttribute(CommonAttributes.USER_ID, user)
                                  .get();

        Long2DoubleOpenHashMap ratings = new Long2DoubleOpenHashMap();
        for (Rating r: history) {
            ratings.put(r.getItemId(), r.getValue());
        }

        return ratings;
    }

}
