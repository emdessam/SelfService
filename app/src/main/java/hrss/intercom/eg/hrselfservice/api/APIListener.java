package hrss.intercom.eg.hrselfservice.api;



public interface APIListener {
	
	public void onSuccess(int id, String url, String response);
	public void onFailure(int id, String url, String response, int responseCode);

}
