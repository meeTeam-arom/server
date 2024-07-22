package com.example.meeTeam.member;

@Entity
@Table(name = {Member})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Member {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

    @member_name
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String member_name;

    @member_password
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String member_password;

    @member_phone_num
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String member_phone_num;

    @member_email
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String member_email;

    @member_role
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String member_role;

    @member_manner_temp
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private double member_manner_temp;

    @member_used_coins
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long member_used_coins;

    @member_earned_coins
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long member_earned_coins;

    @created_at
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String created_at;

    @updated_at
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String updated_at;

    @latitude
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private double latitude;

    @longitude
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private double longitude;

    @available_date
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String available_date;
}
