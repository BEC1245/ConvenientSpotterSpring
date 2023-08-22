package com.convenient.store.foodmix.service;

import com.convenient.store.common.dto.ScrollRequestDTO;
import com.convenient.store.common.dto.ScrollResponseDTO;
import com.convenient.store.common.utill.FileUploader;
import com.convenient.store.foodmix.dto.FoodmixDTO;
import com.convenient.store.foodmix.entity.Foodmix;
import com.convenient.store.foodmix.entity.FoodmixProduct;
import com.convenient.store.foodmix.repository.FoodmixProductRepository;
import com.convenient.store.foodmix.repository.FoodmixRepository;
import com.convenient.store.product.dto.ProductDTO;
import com.convenient.store.product.entity.Product;
import com.convenient.store.product.repository.ProductRepository;
import com.convenient.store.user.entity.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class FoodmixServiceImpl implements FoodmixService {

    private final FoodmixRepository foodmixRepository;
    private final FoodmixProductRepository foodmixProductRepository;
    private final ModelMapper modelMapper;
    private final FileUploader fileUploader;

    @Override
    public ScrollResponseDTO getFoodmixList(ScrollRequestDTO requestDTO) {
        return foodmixRepository.getList(requestDTO);
    }

    @Override
    public FoodmixDTO getOneFoodmix(Long id) {

        // 양쪽의 데이터를 가져오고
        Foodmix foodmix = foodmixRepository.findById(id).orElseThrow();

        List<FoodmixProduct> foodmixProduct = foodmixProductRepository.getOneByMixId(id);

        List<ProductDTO> productDTO = foodmixProduct.stream().map(ele -> modelMapper.map(ele.getProduct(), ProductDTO.class)).collect(Collectors.toList());

        // 해당 데이터를 dto 화
        FoodmixDTO foodmixDTO = modelMapper.map(foodmix, FoodmixDTO.class);

        foodmixDTO.setProducts(productDTO);

        foodmixDTO.setUsers_id(foodmix.getUsers().getId());

        return foodmixDTO;

    }

    // 여기서 넘어오는 imageName은 controller에서 저장된 이름이여야 한다.
    @Override
    public void registFoodmix(FoodmixDTO foodmixDTO) {

        // 본문 저장
        Users users = Users.builder().id(foodmixDTO.getUsers_id()).build();

        Foodmix foodmix = Foodmix.builder()
                .title(foodmixDTO.getTitle())
                .content(foodmixDTO.getContent())
                .imageName(foodmixDTO.getImageName())
                .users(users)
                .build();

        foodmixRepository.save(foodmix);

        // 연결점 저장
        AtomicInteger integer = new AtomicInteger(0);

        foodmixDTO.getProduct_id()
                .stream()
                .forEach(ele -> {

                    FoodmixProduct foodmixProduct = FoodmixProduct.builder()
                            .product(Product.builder().id(ele).build())
                            .foodmix(foodmix)
                            .ord(integer.incrementAndGet())
                            .build();

                    foodmixProductRepository.save(foodmixProduct);

                });
    }

    @Override
    public void modifyFoodmix(FoodmixDTO foodmixDTO) {

        // 꿀조합 부분의 수정
        Foodmix foodmix = foodmixRepository.findById(foodmixDTO.getId()).orElseThrow();

        foodmix.createTitle(foodmix.getTitle());
        foodmix.createContent(foodmix.getContent());

        if(foodmix.getImageName() != null){
            fileUploader.deleteFile("foodmix", foodmix.getImageName());
            foodmix.createImageName(foodmixDTO.getImageName());
        }

        // 그에 관한 연관관계 수정
        List<FoodmixProduct> foodmixProducts = foodmixProductRepository.getOneByMixId(foodmixDTO.getId());

        // 기존의 id
        List<Long> productId = foodmixProducts.stream().map(ele -> ele.getProduct().getId()).collect(Collectors.toList());

        // 새로운 id
        List<Long> oldProductId = foodmixDTO.getProduct_id();




    }

    @Override
    public void deleteFoodmix(Long id) {

        // 상품의 제거
        Foodmix foodmix = foodmixRepository.findById(id).orElseThrow();

        fileUploader.deleteFile("foodmix", foodmix.getImageName());

        foodmix.onDelflag();

        foodmixRepository.save(foodmix);

    }


}
