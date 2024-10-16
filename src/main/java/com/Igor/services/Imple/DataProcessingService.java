package com.Igor.services.Imple;

import com.Igor.dto.FilterDTO;
import com.Igor.dto.UserDTO;
import com.Igor.model.*;
import com.Igor.services.Interf.IProductService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataProcessingService implements IProductService {
    @PersistenceContext
    private EntityManager em;
    public DataProcessingService() {}

    @Override
    public List<Registry> getAll() {
        return new JPAQuery<Registry>(em).select(QRegistry.registry).from(QRegistry.registry).fetch();
    }

    @Transactional
    public List<Registry> searchByParameters(UserDTO userData){
        QRegistry qRegistry = QRegistry.registry;
        BooleanBuilder builder = new BooleanBuilder();

        if (userData.getCompany() != null && !userData.getCompany().isEmpty()){
            builder.and(qRegistry.manufacturer.eq(userData.getCompany().toLowerCase()));
        }
        if (userData.getCountry() != null && !userData.getCountry().isEmpty()){
            builder.and(qRegistry.countryOfOrigin.eq(userData.getCountry().toLowerCase()));
        }
        if (userData.getProductType() != null){
            builder.and(qRegistry.deviceType.name.eq(userData.getProductType()));
        }

        builder.and(qRegistry.onlineOrderAvailable.eq(userData.getOnline()));
        builder.and(qRegistry.installmentAvailable.eq(userData.getInstallment()));
        QDeviceType deviceType = new QDeviceType("deviceType");
        JPAQueryFactory query = new JPAQueryFactory(em);

        return query.selectFrom(qRegistry).leftJoin(qRegistry.deviceType, deviceType).where(builder).fetch();
    }

    @Transactional
    public List<Models> getFilteredData(FilterDTO data){
        return new JPAQuery<Registry>(em).select(QModels.models).
                from(QModels.models).
                where(QModels.models.deviceType.name.eq(data.getDeviceType()).
                        and(QModels.models.color.eq(data.getColor())).
                        and(QModels.models.price.between(data.getLowPriceThreshold(), data.getHighPriceThreshold())))
                .fetch();
    }
}
