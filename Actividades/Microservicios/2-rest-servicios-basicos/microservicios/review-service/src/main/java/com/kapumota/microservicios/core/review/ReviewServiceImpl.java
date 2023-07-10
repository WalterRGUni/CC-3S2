package com.kapumota.microservicios.core.review;

import com.kapumota.api.core.review.Review;
import com.kapumota.api.core.review.ReviewService;
import com.kapumota.util.http.ServiceUtil;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReviewServiceImpl implements ReviewService {

    private final ServiceUtil serviceUtil;

    public ReviewServiceImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<Review> getReviews(int productId) {
        List<Review> reviews = new ArrayList<>();
        Review review1 = new Review(productId, 1, "Lucas",
                "Revisión de " + productId, "Buen producto",
                serviceUtil.getServiceAddress());
        Review review2 = new Review(productId, 2, "Richard",
                "Revisión de " + productId, "Duradero",
                serviceUtil.getServiceAddress());
        reviews.add(review1);
        reviews.add(review2);
        return reviews;
    }
}
