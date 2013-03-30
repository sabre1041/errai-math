package com.redhat.errai.math.client.local;

import javax.enterprise.event.Observes;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.redhat.errai.math.client.shared.Calculation;
import com.redhat.errai.math.client.shared.MathExecution;

/**
 * 
 * @author Andrew Block
 *
 */
public class CalculationStatisticsWidget extends Composite {
	
	private static CalculationStatisticsWidgetClientUiBinder uiBinder = GWT
			.create(CalculationStatisticsWidgetClientUiBinder.class);

	interface CalculationStatisticsWidgetClientUiBinder extends
			UiBinder<Widget, CalculationStatisticsWidget> {
	}
	
	@UiField(provided=true)
	CellList<MathExecution> statisticsTable;
	
	private ListDataProvider<MathExecution> listDataProvider;

	
	
	private MathResources mathResources = GWT.create(MathResources.class);	
	
	public CalculationStatisticsWidget() {
		
		
		MathExecutionCell cell = new MathExecutionCell(mathResources);
		statisticsTable = new CellList<MathExecution>(cell);
		statisticsTable.setPageSize(10);
		statisticsTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
		statisticsTable.setEmptyListWidget(new Label("No Calculations"));
		
		listDataProvider = new ListDataProvider<MathExecution>();
		listDataProvider.addDataDisplay(statisticsTable);
					
		initWidget(uiBinder.createAndBindUi(this));
			
		
	}
	
	
	@UiHandler("clearListButton")
	public void onClearCalculationButton(ClickEvent event)
	{
		listDataProvider.getList().clear();
		statisticsTable.redraw();
	}
	
	public void onCalculationEvent(@Observes @Calculation MathExecution mathExecution)
	{
		listDataProvider.getList().add(0,mathExecution);
		statisticsTable.redraw();
	}
	
}
