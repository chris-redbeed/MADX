/**
 * 
 */
package de.schule.madnx.shared.coder;

import java.util.ArrayList;

import de.schule.madnx.shared.Option;

/**
 * @author xgadscj
 *
 */
public class OptionListCoder {
	
	private static final String ATTR_INDICATOR = "_";
	private static final String OPTION_INDICATOR = "/";
	
	public static ArrayList<Option> decode(String optionList) {
		ArrayList<Option> result = new ArrayList<>();
		if (optionList != null) {
			String[] options = optionList.split(OPTION_INDICATOR);
			for (String s : options) {
				String[] attributes = s.split(ATTR_INDICATOR);
				Option option = new Option();
				option.setName(attributes[0]);
				option.setValue(attributes[1]);
				result.add(option);
			}
		}

		return result;
	}

	public static String encode(ArrayList<Option> optionList) {
		String result = "";
		if (optionList != null && optionList.size() > 0) {
			for (Option o : optionList) {
				result += o.getName() + ATTR_INDICATOR + o.getValue()
						+ OPTION_INDICATOR;
			}
			result.substring(0, result.length() - 1);
		}
		return result;
	}
}
