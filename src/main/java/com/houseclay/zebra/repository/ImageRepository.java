package com.houseclay.zebra.repository;

import com.houseclay.zebra.model.deletelater.Image;
import com.houseclay.zebra.model.rental.Images;
import com.houseclay.zebra.repository.queries.SqlQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {

    @Query(value = SqlQuery.LISTOFPROPERTYIMAGES,nativeQuery = true)
    List<Images> findImagesByImageMap(UUID imageMap);


}
