package org.lenskit.mooc.nonpers.mean;

import it.unimi.dsi.fastutil.longs.LongSet;
import org.lenskit.api.Result;
import org.lenskit.api.ResultList;
import org.lenskit.api.ResultMap;
import org.lenskit.basic.AbstractItemBasedItemRecommender;
import org.lenskit.results.Results;
import org.lenskit.util.collections.LongUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.*;

/**
 * An item scorer that scores each item with its mean rating.
 */
public class MeanItemBasedItemRecommender extends AbstractItemBasedItemRecommender {
    private static final Logger logger = LoggerFactory.getLogger(MeanItemBasedItemRecommender.class);

    private final ItemMeanModel model;

    /**
     * Construct a mean global item scorer.
     *
     * <p>The {@code @Inject} annotation tells LensKit to use this constructor.</p>
     *
     * @param m The model containing item mean ratings.  LensKit will automatically build an {@link ItemMeanModel}
     *          object.  Its use as a parameter type in this constructor declares it as a <em>dependency</em> of the
     *          mean-based item scorer.
     */
    @Inject
    public MeanItemBasedItemRecommender(ItemMeanModel m) {
        model = m;
    }

    /**
     * {@inheritDoc}
     *
     * This is the LensKit recommend method.  It takes several parameters; we implement it for you in terms of a
     * simpler method ({@link #recommendItems(int, LongSet)}).
     */
    @Override
    public ResultList recommendRelatedItemsWithDetails(Set<Long> basket, int n, @Nullable Set<Long> candidates, @Nullable Set<Long> exclude) {
        LongSet items;
        if (candidates == null) {
            items = model.getKnownItems();
        } else {
            items = LongUtils.asLongSet(candidates);
        }

        if (exclude != null) {
            items = LongUtils.setDifference(items, LongUtils.asLongSet(exclude));
        }

        logger.info("computing {} recommendations from {} items", n, items.size());

        return recommendItems(n, items);
    }

    /**
     * Recommend some items from a set of candidate items.
     *
     * <p>Your code needs to obtain the mean rating, if one is available, for each item, and return a list of the
     * {@code n} highest-rated items, in decreasing order of score.</p>
     *
     * <p>To create the {@link ResultMap} data structure, do the following:</p>
     *
     * <ol>
     *     <li>Create a {@link List} to hold {@link Result} objects.</li>
     *     <li>Create a result object for each item that can be scored.  Use {@link Results#create(long, double)} to
     *     create the result object. If an item cannot be scored (because there is no mean available), ignore it and
     *     do not add a result to the list.</li>
     *     <li>Convert the list of results to a {@link ResultList} using {@link Results#newResultList(List)}.</li>
     * </ol>
     *
     * @param n The number of items to recommend.  If this is negative, then recommend all possible items.
     * @param items The items to score.
     * @return A {@link ResultMap} containing the scores.
     */
    private ResultList recommendItems(int n, LongSet items) {
        List<Result> results = new ArrayList<>();

        // TODO Find the top N items by mean rating
        if ( n<0 ) {
            n = items.size();
        }
        List<Result> results_tmp = new ArrayList<>();
        Iterator it = items.iterator();
        while (it.hasNext()) {
            Long itemId = (Long)it.next();
            if (model.hasItem(itemId)) {
                Result r = Results.create(itemId, model.getMeanRating(itemId));
                results_tmp.add(r);
//                logger.info("scanning item {}", r.getScore());
            }
        }

//        results_tmp.sort(Comparator.comparing(Result::getScore));
        Collections.sort(results_tmp, new Comparator<Result>() {
            public int compare(Result o1, Result o2) {
                return Double.valueOf(o2.getScore()).compareTo(Double.valueOf(o1.getScore()));
            }
        });

        for(Result r: results_tmp) {
            if (n==0) break;
            results.add(r);
//            logger.info("scanning item {}", r.getScore());
            n--;
        }

        return Results.newResultList(results);
    }
}
