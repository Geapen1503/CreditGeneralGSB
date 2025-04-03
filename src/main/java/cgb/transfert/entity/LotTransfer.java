package cgb.transfert.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "LOT_TRANSFER")
public class LotTransfer {
    @Id
    private Long id;
    private String refLot;
    private String sourceAccount;
	private String descriptionLot;
	private LocalDateTime dateLancement;
    private String etat;
    
    @OneToMany(mappedBy = "lotTransfer", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
    private List<Transfer> transfers;

    public LotTransfer() {
        this.refLot = UUID.randomUUID().toString();
        this.dateLancement = LocalDateTime.now();
        this.etat = "En cours";
    }

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
    public String getNumeroDeLot() {
		return refLot;
	}
	public void setNumeroDeLot(String numeroDeLot) {
		this.refLot = numeroDeLot;
	}
	public String getSourceAccount() {
		return sourceAccount;
	}
	public void setSourceAccount(String sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	public String getDescriptionLot() {
		return descriptionLot;
	}
	public void setDescriptionLot(String descriptionLot) {
		this.descriptionLot = descriptionLot;
	}
	public LocalDateTime getDateLancement() {
		return dateLancement;
	}
	public void setDateLancement(LocalDateTime dateLancement) {
		this.dateLancement = dateLancement;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public List<Transfer> getTransfers() {
		return transfers;
	}
	public void setTransfers(List<Transfer> transfers) {
		this.transfers = transfers;
	}
}


