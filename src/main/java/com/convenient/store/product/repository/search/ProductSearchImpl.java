package com.convenient.store.product.repository.search;

import com.convenient.store.product.dto.PageRequestDTO;
import com.convenient.store.product.dto.PageResponseDTO;
import com.convenient.store.product.dto.ProductListWithRcntDTO;
import com.convenient.store.product.entity.Product;
import com.convenient.store.product.entity.QProduct;
import com.convenient.store.product.entity.QReview;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {

    public ProductSearchImpl() {
        super(Product.class);
    }

    @Override
    public PageResponseDTO<ProductListWithRcntDTO> list(PageRequestDTO pageRequestDTO) {

        // 1. Q객체를 만든다
        QProduct product = QProduct.product;
        QReview review = QReview.review;


        // 2. 쿼리를 만든다
        JPQLQuery<Product> query = from(product);
        query.leftJoin(review).on(review.product.eq(product));
        query.groupBy(product);


        // 여기서 정렬 조건은 [pa, pd, rv]
        String ordType = pageRequestDTO.getOrderBy();
        if(ordType != null && ordType.length() != 0){
            switch (ordType){
                case "pa" -> query.orderBy(product.price.asc());
                case "pd" -> query.orderBy(product.price.desc());
                case "rv" -> query.orderBy(review.score.avg().asc());
            }
        }


        // 3. 패이징 처리에 필요한 정보를 제공한다.
        int page = pageRequestDTO.getPage() - 1;
        int limit = pageRequestDTO.getLimit();
        Pageable pageable = PageRequest.of(page, limit, Sort.by("id"));
        this.getQuerydsl().applyPagination(pageable, query);


        // 4. booleanBuilder로 검색 결과 제공
        // 여기서 변수는 keyword, searchType인데 searchType은 [n, e, s] name, (event)state, sname

        String searchType = pageRequestDTO.getSearchType();

        if(searchType != null && searchType.length() != 0){

            String[] searchTypes = searchType.split("");

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            String keyword = pageRequestDTO.getKeyword();

            for(String type : searchTypes){

                switch (type){
                    case "n" -> booleanBuilder.or(product.pname.eq(keyword));
                    case "e" -> booleanBuilder.or(product.state.eq(keyword));
                    case "s" -> booleanBuilder.or(product.state.eq(keyword));
                }

            }

            query.where(booleanBuilder);

        }

        // 5. 결과를 return
        JPQLQuery<ProductListWithRcntDTO> fetch = query.select(Projections.bean(ProductListWithRcntDTO.class,
                product.id,
                product.pname,
                product.price,
                product.state,
                product.sname,
                product.img,
                review.score.avg().as("avgScore"),
                review.count().as("reviewCount")
                ));

        List<ProductListWithRcntDTO> list = fetch.fetch();

        Long total = fetch.fetchCount();

        return new PageResponseDTO<>(list, total + 1, pageRequestDTO);
    }
}
