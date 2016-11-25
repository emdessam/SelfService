package hrss.intercom.eg.hrselfservice.api;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultPendingRequest {

    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("lstVacReq")
    @Expose
    private List<LstVacReq> lstVacReq = new ArrayList<LstVacReq>();
    @SerializedName("lstPerReq")
    @Expose
    private List<LstPerReq> lstPerReq = new ArrayList<LstPerReq>();
    @SerializedName("lstCompReq")
    @Expose
    private List<LstCompReq> lstCompReq = new ArrayList<LstCompReq>();
    @SerializedName("lstMisReq")
    @Expose
    private List<LstMisReq> lstMisReq = new ArrayList<LstMisReq>();

    /**
     *
     * @return
     * The result
     */
    public Integer getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(Integer result) {
        this.result = result;
    }

    /**
     *
     * @return
     * The lstVacReq
     */
    public List<LstVacReq> getLstVacReq() {
        return lstVacReq;
    }

    /**
     *
     * @param lstVacReq
     * The lstVacReq
     */
    public void setLstVacReq(List<LstVacReq> lstVacReq) {
        this.lstVacReq = lstVacReq;
    }

    /**
     *
     * @return
     * The lstPerReq
     */
    public List<LstPerReq> getLstPerReq() {
        return lstPerReq;
    }

    /**
     *
     * @param lstPerReq
     * The lstPerReq
     */
    public void setLstPerReq(List<LstPerReq> lstPerReq) {
        this.lstPerReq = lstPerReq;
    }

    /**
     *
     * @return
     * The lstCompReq
     */
    public List<LstCompReq> getLstCompReq() {
        return lstCompReq;
    }

    /**
     *
     * @param lstCompReq
     * The lstCompReq
     */
    public void setLstCompReq(List<LstCompReq> lstCompReq) {
        this.lstCompReq = lstCompReq;
    }

    /**
     *
     * @return
     * The lstMisReq
     */
    public List<LstMisReq> getLstMisReq() {
        return lstMisReq;
    }

    /**
     *
     * @param lstMisReq
     * The lstMisReq
     */
    public void setLstMisReq(List<LstMisReq> lstMisReq) {
        this.lstMisReq = lstMisReq;
    }

}
