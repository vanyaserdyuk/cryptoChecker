package com.micro.cryptoChecker.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "jobs")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class PriceCheckBackgroundJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @NonNull
    private String currency;

    @Getter
    @Setter
    @NonNull
    private int targetPrice;


//    @Transient
//    private Runnable execution;
}
