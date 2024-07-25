package com.example.meeTeam.image;

import com.example.meeTeam.global.entity.BaseEntity;
import com.example.meeTeam.item.Item;
import com.example.meeTeam.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "image")
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url", nullable = false)
    private String url;

    @OneToOne(mappedBy = "image")
    private Item item;

//    public void updateUrl(String changedUrl) {
//        this.url = changedUrl;
//        member.setImage(this);
//    }
}
