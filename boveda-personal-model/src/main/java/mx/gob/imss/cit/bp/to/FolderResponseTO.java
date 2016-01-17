package mx.gob.imss.cit.bp.to;

public class FolderResponseTO extends BaseResponseTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7846899634447053169L;
	
	private FolderTO folder;

	public FolderTO getFolderTO() {
		return folder;
	}

	public void setFolderTO(FolderTO folder) {
		this.folder = folder;
	}
	
	
}
