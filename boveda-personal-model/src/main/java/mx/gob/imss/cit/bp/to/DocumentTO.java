
package mx.gob.imss.cit.bp.to;

public class DocumentTO
    extends BaseObjectTO
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 70153186427549904L;
	
	protected String content;
    protected String mimeType;
    protected String ext;
    
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getMimeType() {
		return mimeType;
	}
	
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	public String getExt() {
		return ext;
	}
	
	public void setExt(String ext) {
		this.ext = ext;
	}

    
}
