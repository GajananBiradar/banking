package com.houseclay.zebra.model.nationalid;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tbl_national_id_docs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class NationalIdDocument {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID national_doc_id;

    private String documentName;

    private String contentType;

    private Long size;

    @Lob
    private byte[] document_data;

}
