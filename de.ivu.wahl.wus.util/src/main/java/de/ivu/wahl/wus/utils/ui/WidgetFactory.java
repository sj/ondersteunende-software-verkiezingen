/*
 * WidgetFactory
 * 
 * Created on Nov 19, 2008
 * Copyright (c) 2008 Kiesraad
 */

package de.ivu.wahl.wus.utils.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import de.ivu.wahl.wus.utils.i18n.Messages;
import de.ivu.wahl.wus.utils.ui.provider.ObjectArrayContentProvider;
import de.ivu.wahl.wus.utils.ui.provider.ToStringLabelProvider;
import de.ivu.wahl.wus.utils.ui.widget.GBATextField;

public class WidgetFactory {

  private static final Logger LOGGER = Logger.getLogger(WidgetFactory.class);

  public static final Country[] COUNTRIES = readCountries();

  public static Text createText(final Composite parent, final int style) {
    final Text w = new Text(parent, style);
    return w;
  }

  public static Text createText(final Composite parent, final int style, final String tooltip) {
    final Text w = new Text(parent, style);
    addFieldHelp(w, tooltip);
    return w;
  }

  public Combo createCityCombo(final Composite parent, final int style) {
    final Combo w = new Combo(parent, style);
    final List<String> values = getCities();
    for (final String v : values) {
      w.add(v);
    }
    return w;
  }

  public static ComboViewer createCountryCombo(final Composite parent, final int style) {
    final Combo w = new Combo(parent, style);
    final ComboViewer viewer = new ComboViewer(w);
    viewer.setContentProvider(new ObjectArrayContentProvider());
    viewer.setLabelProvider(new ToStringLabelProvider());
    viewer.setInput(COUNTRIES);
    addFieldHelp(w, Messages.widgetFactoryHelpTooltipCountry);
    return viewer;
  }

  public static ComboViewer createLanguageCombo(Composite parent, int style, Object input) {
    Combo w = new Combo(parent, style);
    ComboViewer viewer = new ComboViewer(w);
    viewer.setContentProvider(new ObjectArrayContentProvider());
    viewer.setLabelProvider(new ToStringLabelProvider());
    viewer.setInput(input);
    //    addFieldHelp(w, Messages.widgetFactoryHelpTooltipCountry);
    return viewer;
  }

  private static Country[] readCountries() {
    final List<Country> countries = new ArrayList<Country>();
    final List<String> lines = ReadFileUtil.readListFromFile("countries.txt"); //$NON-NLS-1$
    for (final String l : lines) {
      final String[] tokens = l.split("\t", 2); //$NON-NLS-1$
      Validate.isTrue(tokens.length == 2, "" + tokens.length + l); //$NON-NLS-1$
      countries.add(new Country(tokens[0], tokens[1]));
    }
    LOGGER.debug("Read countries. size: " + countries.size()); //$NON-NLS-1$
    return countries.toArray(new Country[countries.size()]);
  }

  public static Text createCityText(final Composite parent, final int style) {
    final Text cityText = createText(parent, style);
    final List<String> cities = getCities();
    final String[] cityProposals = cities.toArray(new String[cities.size()]);
    final String[] gbaProposals = getGbaProposals();
    new CityAndGBAAutoCompleteField(cityText, new TextContentAdapter(), cityProposals, new TextContentAdapter(),
        gbaProposals);
    addFieldHelp(cityText, Messages.widgetFactoryHelpTooltipCity);

    return cityText;
  }

  public static List<String> getCities() {
    final List<String> cities = ReadFileUtil.readListFromFile("cities.txt"); //$NON-NLS-1$
    return cities;
  }

  public static String[] getGbaProposals() {
    // read GBA characters from file. each line start with base character as first char, blank if
    // any
    final List<String> gbaChars = ReadFileUtil.readListFromFile("gba_classes.txt"); //$NON-NLS-1$
    return gbaChars.toArray(new String[gbaChars.size()]);
  }

  public static Text createGbaText(final Composite parent, final int style) {
    final Text gbaText = createText(parent, style);
    final String[] proposals = getGbaProposals();
    new GBAAutoCompleteField(gbaText, new TextContentAdapter(), proposals);

    return gbaText;
  }

  public static Text createGbaText(final Composite parent, final int style, final String tooltip) {
    final Text field = createGbaText(parent, style);
    addFieldHelp(field, tooltip);

    return field;
  }

  public static GBATextField createGbaTextField(final Composite parent, final int style) {
    final GBATextField gbaTextField = new GBATextField(parent, style);
    final String[] proposals = getGbaProposals();
    gbaTextField.setGbaAutoCompleteField(new GBAAutoCompleteField(gbaTextField.getText(), new TextContentAdapter(),
        proposals));

    return gbaTextField;
  }

  public static Label createLabel(final Composite parent, final int style) {

    final Label label = new Label(parent, style);
    return label;
  }

  public static Label createFieldLabel(final Composite parent, final int style) {

    final Label label = createLabel(parent, style);
    label.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));

    return label;
  }

  /**
   * add a field help (tooltip) icon right of the field
   * 
   * @param field
   * @param tooltip
   */
  public static void addFieldHelp(final Control field, final String tooltip) {
    final String decoratorId = FieldDecorationRegistry.DEC_INFORMATION;
    final Image image = FieldDecorationRegistry.getDefault().getFieldDecoration(decoratorId).getImage();
    final ControlDecoration decoration = new ControlDecoration(field, SWT.RIGHT | SWT.CENTER);
    decoration.setImage(image);
    decoration.setDescriptionText(tooltip);
    decoration.setMarginWidth(0);
  }

  public static Image createImage(final String pluginId, final String path) {
    return AbstractUIPlugin.imageDescriptorFromPlugin(pluginId, path).createImage();
  }
}
