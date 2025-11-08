package com.kiwipay.onboarding.catalog.domain.model.aggregates;

import com.kiwipay.onboarding.catalog.domain.model.entities.Province;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    private String id;
    private String name;

    @OneToMany(mappedBy = "department")
    @JsonManagedReference("dept-provinces")
    private List<Province> provinces;
}
