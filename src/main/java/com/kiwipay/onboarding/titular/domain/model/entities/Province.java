package com.kiwipay.onboarding.titular.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Province {
    @Id
    private String id;
    private String name;

    @ManyToOne
    @JsonBackReference("dept-provinces")
    private Department department;

    @OneToMany(mappedBy = "province")
    @JsonManagedReference("prov-districts")
    private List<District> districts;
}
