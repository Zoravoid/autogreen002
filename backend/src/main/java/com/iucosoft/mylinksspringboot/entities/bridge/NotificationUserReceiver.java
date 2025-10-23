package com.iucosoft.mylinksspringboot.entities.bridge;

import com.iucosoft.mylinksspringboot.entities.Notification;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "notifications_user_receiver")
public class NotificationUserReceiver extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "notification_id", nullable = false)
    private Notification notification;

    @ManyToOne
    @JoinColumn(name = "user_receiver_id", nullable = false)
    private User userReceiver;


}
