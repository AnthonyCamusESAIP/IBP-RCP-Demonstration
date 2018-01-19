package beans;
import java.util.Date;

import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;

@ManagedBean
public class DatePicker {

	private Date date;
	
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
    
}
