package com.kapumota.microservicios.core.recommendation;

import com.kapumota.api.core.recommendation.Recommendation;
import com.kapumota.api.core.recommendation.RecommendationService;
import com.kapumota.util.http.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecommendationServiceImpl implements RecommendationService {

    private final ServiceUtil serviceUtil;

    @Autowired
    public RecommendationServiceImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<Recommendation> getRecommendations(int productId) {
        List<Recommendation> recommendations = new ArrayList<>();
        Recommendation recommendation1 = new Recommendation(productId, 1,
                "Carlos", 4, "bueno", serviceUtil.getServiceAddress());
        Recommendation recommendation2 = new Recommendation(productId, 2,
                "Juan", 3, "regular", serviceUtil.getServiceAddress());
        recommendations.add(recommendation1);
        recommendations.add(recommendation2);
        return recommendations;
    }
}