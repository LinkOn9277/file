/*********************************************************
 * 조회작업이 여러개일 경우 메소드가 필요
 *********************************************************/
package com.team.itemfileupload.Repository;

import com.team.itemfileupload.Entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {



}
