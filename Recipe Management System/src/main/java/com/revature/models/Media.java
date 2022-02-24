package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="media")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id", updatable = false)
    private int id;

    @Column(name = "main_image")
    private boolean mainImage;

    @Column(length = 50)
    private String type;

    @Column(length = 50)
    private String title;

    @Lob
    private byte[] data;
}
