package com.example.swiggato.repositories;

import com.example.swiggato.models.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner,Integer> {


    String findRandomPartner = "Select dp from DeliveryPartner dp order by RAND() LIMIT 1";
    @Query(value = findRandomPartner)
    public DeliveryPartner findRandomDeliveryPartner();




    String emailToPartnerWithLessDeliveriesHQL="select d from OrderEntity o inner join DeliveryPartner d on o.deliveryPartner.id=d.id where o.orderTime>=(current_date - :days day) group by d.id having count(d.id)<:noOfDeliveries";
    String emailToPartnerWithLessDeliveries="select d.* from order_entity o inner join delivery_partner d on o.delivery_partner_id=d.id where order_time>=date_sub(current_date(),interval :days day) group by d.id having count(d.id)<:noOfDeliveries";
    @Query(value = emailToPartnerWithLessDeliveriesHQL)
    public List<DeliveryPartner> sendEmailToPartnerWithLessDeliveries(int days,int noOfDeliveries);
}
