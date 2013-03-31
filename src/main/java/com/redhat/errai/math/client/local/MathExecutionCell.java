package com.redhat.errai.math.client.local;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.redhat.errai.math.client.shared.Manufacturer;
import com.redhat.errai.math.client.shared.MathExecution;

/**
 * 
 * @author Andrew Block
 *
 */
public class MathExecutionCell extends AbstractCell<MathExecution> {

	private MathResources mathResources;

	private static final DateTimeFormat dateFormat = DateTimeFormat
			.getFormat("MM/dd/yy hh:mm:ss");

	public MathExecutionCell(MathResources mathResources) {
		this.mathResources = mathResources;
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			MathExecution mathExecution, SafeHtmlBuilder sb) {

		if (mathExecution == null) {
			return;
		}

		sb.appendHtmlConstant("<table width='100%'>");

		// Add the contact image.
		sb.appendHtmlConstant("<tr><td width='25%'>");

		ImageResource browserImage = getBrowserImage(mathExecution
				.getUserEnvironment().getBrowser());

		if (browserImage != null) {
			sb.appendHtmlConstant(AbstractImagePrototype.create(browserImage)
					.getHTML());
		}

		ImageResource operatingSystemImage = getOperatingSystemImage(mathExecution
				.getUserEnvironment().getOperationSystem());

		if (operatingSystemImage != null) {
			sb.appendHtmlConstant(AbstractImagePrototype.create(
					operatingSystemImage).getHTML());
		}

		sb.appendHtmlConstant("</td>");

		// Add the name and address.
		sb.appendHtmlConstant("<td>");
		sb.appendHtmlConstant(mathExecution.getExecution());
		sb.appendHtmlConstant("</td><td style='font-size:75%' valign='bottom'>");

		sb.appendHtmlConstant(dateFormat.format(mathExecution.getEventDate()));

		sb.appendHtmlConstant("</table>");

	}

	private ImageResource getBrowserImage(Manufacturer manufacturer) {
		if (manufacturer != null) {
			switch (manufacturer) {
			case APPLE:
				return mathResources.safari();
			case MOZILLA:
				return mathResources.firefox();
			case MICROSOFT:
				return mathResources.ie8700();
			case OPERA:
				return mathResources.opera();
			case GOOGLE:
				return mathResources.chrome();

			}
		}

		return mathResources.unknown();

	}

	private ImageResource getOperatingSystemImage(Manufacturer manufacturer) {
		if (manufacturer != null) {
			switch (manufacturer) {
			case APPLE:
				return mathResources.mac();
			case MICROSOFT:
				return mathResources.windows();
			case BLACKBERRY:
				return mathResources.blackberry();
			case LINUX:
				return mathResources.linux();
			case GOOGLE:
				return mathResources.android();
			}

		}

		return mathResources.unknown();

	}

}
