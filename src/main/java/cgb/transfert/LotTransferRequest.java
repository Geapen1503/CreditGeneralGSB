package cgb.transfert;

import java.util.List;

import cgb.transfert.entity.LotTransfer;
import lombok.*;


@Data
public class LotTransferRequest {
	private Long id;
	private String refLot;
    private String sourceAccount;
	private String descriptionLot;
	private List<LotTransfer> listLotTransfer;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRefLot() {
		return refLot;
	}
	public void setRefLot(String refLot) {
		this.refLot = refLot;
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
	public List<LotTransfer> getListLotTransfer() {
		return listLotTransfer;
	}
	public void setListLotTransfer(List<LotTransfer> listLotTransfer) {
		this.listLotTransfer = listLotTransfer;
	}

}
