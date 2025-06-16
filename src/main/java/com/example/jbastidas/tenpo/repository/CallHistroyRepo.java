package com.example.jbastidas.tenpo.repository;


import com.example.jbastidas.tenpo.entity.CallHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface CallHistroyRepo extends JpaRepository<CallHistoryEntity, Long> {

    Optional<CallHistoryEntity> findFirstByCodigoOrderByFechaDesc(int codigo);

}
