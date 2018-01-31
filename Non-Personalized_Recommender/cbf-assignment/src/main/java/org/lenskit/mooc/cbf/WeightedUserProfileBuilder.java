package org.lenskit.mooc.cbf;

import org.lenskit.data.ratings.Rating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Build a user profile from all positive ratings.
 */
public class WeightedUserProfileBuilder implements UserProfileBuilder {
    private static final Logger logger = LoggerFactory.getLogger(TFIDFModelProvider.class);
    /**
     * The tag model, to get item tag vectors.
     */
    private final TFIDFModel model;

    @Inject
    public WeightedUserProfileBuilder(TFIDFModel m) {
        model = m;
    }

    @Override
    public Map<String, Double> makeUserProfile(@Nonnull List<Rating> ratings) {
        // Create a new vector over tags to accumulate the user profile
        Map<String,Double> profile = new HashMap<>();
        Double length_r = Double.valueOf(0);
        Double sum_r = Double.valueOf(0);
        // TODO Normalize the user's ratings
        for (Rating r: ratings) {
//            logger.info("rating {}", r.getValue());
            length_r += r.getValue() * r.getValue();
            sum_r += r.getValue();
        }
        double avg_r = sum_r/Double.valueOf(ratings.size());
//        logger.info("rating avg {}", avg_r);
        length_r = Math.sqrt(length_r);
        // TODO Build the user's weighted profile
        // Iterate over the user's ratings to build their profile
        for (Rating r: ratings) {
            Map<String, Double> itemTagVec = model.getItemVector(Long.valueOf(r.getItemId()));
//                logger.info("Scanning item {} vector {}", r.getItemId(), itemTagVec);

            for (String key: itemTagVec.keySet()) {
                double val = (r.getValue() - avg_r) * itemTagVec.get(key) / length_r;
//                logger.info("rating val {}", val);
                if (profile.containsKey(key)) {
                    profile.put(key, profile.get(key) + val);
                } else {
                    profile.put(key, val);
                }

            }
        }

        // The profile is accumulated, return it.
        return profile;
    }
}
