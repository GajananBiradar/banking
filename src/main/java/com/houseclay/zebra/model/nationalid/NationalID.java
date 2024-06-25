package com.houseclay.zebra.model.nationalid;

import com.houseclay.zebra.model.common.BaseTimeStamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tbl_nationalid")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class NationalID {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID" , strategy = "org.hibernate.id.UUIDGenerator")
    private UUID national_id;

    private NationalIDType nationalIdType;

    private String nationalIDNumber; //make this encrypted

    @OneToOne(targetEntity = NationalIdDocument.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "document_file",referencedColumnName = "national_doc_id")
    private NationalIdDocument nationalIdDocument;

    private boolean isVerfied; //verify by the houseclay captain

    @Embedded
    private BaseTimeStamp baseTimeStamp;

}
