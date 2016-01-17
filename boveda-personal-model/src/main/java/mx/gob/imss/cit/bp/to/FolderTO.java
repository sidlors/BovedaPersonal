package mx.gob.imss.cit.bp.to;

import java.util.List;


public class FolderTO
    extends BaseObjectTO
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6392127245642586955L;
	
	protected List<FolderTO> children;

	public List<FolderTO> getChildren() {
		return children;
	}

	public void setChildren(List<FolderTO> children) {
		this.children = children;
	}
}
