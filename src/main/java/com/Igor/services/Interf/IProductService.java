package com.Igor.services.Interf;

import com.Igor.model.Registry;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Optional<List<Registry>> getAll();
}
