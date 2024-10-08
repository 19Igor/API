package com.Igor.services.Imple;

import com.Igor.dto.UserDTO;
import com.Igor.model.QDeviceType;
import com.Igor.model.QRegistry;
import com.Igor.model.Registry;
import com.Igor.repository.RegistryRepository;
import com.Igor.services.Interf.IProductService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataProcessingService implements IProductService {
    @Autowired
    private RegistryRepository registryRepository;
    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory factory = new JPAQueryFactory(em);

    public DataProcessingService() {}

    @Override
    public Optional<List<Registry>> getAll() {
        registryRepository.findAll();
        return null;
    }

    @Transactional
    public List<Registry> searchByParametrs(UserDTO userData){
        /*
          Ещё один момент. Тип дивайся будет приходить как название - не id.
          Это значит, что нужно писать запрос с join для того, чтобы мы могли сразу же
          узнать наименование типа запрашиваемого товара.
          */

        // productType country company isOnline isInstallment
        QRegistry qRegistry = QRegistry.registry;

        BooleanBuilder builder = new BooleanBuilder();
        if (userData.getCompany() != null && !userData.getCompany().isEmpty()){
            builder.and(qRegistry.manufacturer.eq(userData.getCompany().toLowerCase()));
        }
        if (userData.getCountry() != null && !userData.getCountry().isEmpty()){
            builder.and(qRegistry.countryOfOrigin.eq(userData.getCountry().toLowerCase()));
        }
        /*
        * Если пользователь не указал тип товара, то фронт должен вернуть отрицательное число.
        * */
        if (userData.getProductType() <= 0){
            builder.and(qRegistry.deviceTypeId.eq(userData.getProductType()));
        }
        // todo: по дефолту, postman возвращает false, если никаких данных не было введено. Нужно обработать эту ситуацию.
        builder.and(qRegistry.onlineOrderAvailable.eq(userData.getOnline()));
        builder.and(qRegistry.installmentAvailable.eq(userData.getInstallment()));
        QDeviceType deviceType = new QDeviceType("deviceType");

        JPAQueryFactory query = new JPAQueryFactory(em);
        return query.selectFrom(qRegistry).leftJoin(qRegistry.deviceTypeId, deviceType.name).where(builder).fetch();
    }

}
