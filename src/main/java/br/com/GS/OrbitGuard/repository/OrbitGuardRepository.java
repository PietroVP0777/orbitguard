package br.com.GS.OrbitGuard.repository;

import br.com.GS.OrbitGuard.model.FocosIncendios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrbitGuardRepository extends JpaRepository<FocosIncendios, Long> {

    Optional<FocosIncendios> findByLatitudeAndLongitude(double latitude, double longitude);

    List<FocosIncendios> findByPais(String pais);

//    @Query("""
//    SELECT f FROM FocosIncedios f
//    WHERE ABS(f.latitude - :latitude) < :tolerancia
//    AND ABS(f.longitude - :longitude) < :tolerancia
//    ORDER BY f.data DESC
//    """)
//    List<FocosIncedios> buscarCoordenadasProximas(
//            double latitude,
//            double longitude,
//            double tolerancia
//    );

}
