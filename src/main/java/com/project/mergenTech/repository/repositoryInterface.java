package com.project.mergenTech.repository;

import com.project.mergenTech.entity.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;


import javax.management.ValueExp;
import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@CrossOrigin
public interface repositoryInterface extends JpaRepository<Master,Long> {





   // @Query(value = "select u.first_name as firstName, u.last_name as lastName, count(o.id) as orderCount from user u left join orders o on u.id = o.user_id  group by u.first_name, u.last_name;"
           // , nativeQuery = true)
  // @Query(value="SELECT m.M_ID as 'm_Id', m.HOSPITAL_NAME as 'hastaneAdi', m.IP_ADDRESS as 'ipAdresi',m.MAC_ADDRESS as 'macAdresi', m.USER_CODE as 'kullaniciKodu' m.MESSAGE_DESCRIBE as 'mesajTanimi' ,m.TIME as 'zaman' FROM MASTER m left join THROWABLE t on m.M_ID=t.MASTER_M_ID  group by m.HOSPITAL_NAME ",nativeQuery = true)
   @Query(value="SELECT m FROM Master m")
    List<Master> getAll();

    @Query(value = "SELECT m FROM Master m Where m.m_Id=:id")
    List<Master> findAllById(Long id);
}
