package com.project.mergenTech.service.Impl;

import com.project.mergenTech.entity.Filtre;
import com.project.mergenTech.entity.Master;
import com.project.mergenTech.repository.repositoryInterface;
import com.project.mergenTech.service.serviceInterface;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class serviceImpl implements serviceInterface {


    @PersistenceUnit
    private EntityManagerFactory em;

    private final repositoryInterface repositoryinterface;


    @Override
    public void exceptionOrnek(Filtre filtre) throws ServiceException {
        if (filtre.getSonTarih() != null) {
            if (filtre.getSonTarih().compareTo(filtre.getIlkTarih()) < 0) {
                throw new ServiceException("Lütfen geçerli bir tarih aralığı seçiniz!");
            }
        }
        if (filtre.getHastaneAdi() == null && filtre.getHastaneAdi().isEmpty() && filtre.getHastaneAdi().isBlank()) {
            throw new ServiceException("Hastane adı boş olamaz!");
        }

    }


    @Override
    public List<Master> getMastersf(Filtre filtre) {

        exceptionOrnek(filtre);
        EntityManager eManager = em.createEntityManager();
        String query = "select s from Master s WHERE 0=0 ";
        if (filtre.getHastaneAdi() != null) {

            query += " AND s.hastaneAdi= :hastaneadi ";

        }
        if (filtre.getKullaniciKodu() != null) {

            query += " AND s.kullaniciKodu= :kullanicikodu ";

        }
        if (filtre.getIlkTarih() != null) {

            query += " AND s.zaman > :ilktarih AND s.zaman < :sontarih";
        }

        Query q = eManager.createQuery(query);
        if (filtre.getHastaneAdi() != null) {
            q.setParameter("hastaneadi", filtre.getHastaneAdi());
        }
        if (filtre.getKullaniciKodu() != null) {
            q.setParameter("kullanicikodu", filtre.getKullaniciKodu());
        }
        if (filtre.getIlkTarih() != null) {
            if (filtre.getSonTarih() != null) {
                q.setParameter("ilktarih", filtre.getIlkTarih());
                q.setParameter("sontarih", filtre.getSonTarih());
            }
        }

        List<Master> supportersList = new ArrayList<Master>();
        supportersList = q.getResultList();

        return supportersList;
    }


    @Override
    public List<Master> findallbyentity(Object kriter) {
        EntityManager entityManager = em.createEntityManager();
        String query = "SELECT m FROM Master m WHERE m.kullaniciKodu:kriter[0]";
//        Query Qer=entityManager.createQuery(query);
//        List<Master> resultMaster=Qer.getResultList();
        Query Qer = entityManager.createQuery(query);
        List<Master> resultMaster = Qer.getResultList();
        return resultMaster;
    }


    @Override
    public Master createMaster(Master master) {
        master.getThrowables().forEach(throwable -> throwable.setMaster(master));
        return repositoryinterface.save(master);
    }

    @Override
    public List<Master> getAll() {
        return repositoryinterface.getAll();
    }

    @Override
    public List<Master> findAllById(Long id) {
        return repositoryinterface.findAllById(id);
    }


}
