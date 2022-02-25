package com.project.mergenTech.service;


import com.project.mergenTech.entity.Filtre;
import com.project.mergenTech.entity.Master;
import org.hibernate.service.spi.ServiceException;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface serviceInterface {
    Master createMaster(Master master);

    List<Master> getAll();

    List<Master> findAllById(Long id);

    List<Master> findallbyentity(Object kriter);

    List<Master> getMastersf(Filtre filtre);

    void exceptionOrnek(Filtre filtre) throws ServiceException;;
}
