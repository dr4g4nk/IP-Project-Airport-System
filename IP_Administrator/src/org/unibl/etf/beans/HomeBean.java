package org.unibl.etf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.unibl.etf.dao.AccessDao;
import org.unibl.etf.dto.Access;

@ManagedBean
@SessionScoped
public class HomeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -538876126372279309L;
	private LineChartModel lineModel;
	
	public HomeBean() {
		lineModel = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        
        List<Access> accesses = AccessDao.getAccesses();
        
        accesses.forEach(a ->{
        	values.add(a.getBrojPristupa());
        	labels.add(a.getDatum());
        });
        
        dataSet.setLabel("Pristup korisnickoj aplikaciji u poslednih 30 dana");
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setLineTension(0.5);
        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        lineModel.setData(data);
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}



}
