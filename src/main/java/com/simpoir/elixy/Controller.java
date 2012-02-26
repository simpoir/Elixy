package com.simpoir.elixy;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zul.SimpleTreeModel;
import org.zkoss.zul.SimpleTreeNode;

import com.simpoir.elixy.view.LocalTreeNode;

public class Controller {

	private static Log _log = LogFactory.getLog(Controller.class);
	private static Controller _instance = null;
	private static Properties _props = null;

	public static Controller getInstance() {
		if (null == _instance) {
			_instance = new Controller();
		}
		return _instance;
	}

	public static Properties getProps() {
		if (null == _props) {
			_props = new Properties();
			try {
				_props.load(Controller.class.getResourceAsStream(
						"build.properties"));
			} catch (IOException e) {
				_log.fatal("build.properties not found in build");
			}

			// load custom config
			String cfgFile = _props.getProperty("CONFIG_FILE");
			if (null != cfgFile) {
				try {
					_props.load(new FileInputStream(cfgFile));
				} catch (IOException e) {
					_log.fatal(MessageFormat.format(
							"Configuration {0} could not be loaded.",
							new Object[] {cfgFile}));
				}
			}
		}
		return _props;
	}

	public SimpleTreeModel getTreeModel() {
		// TODO proper load the tree model
		SimpleTreeNode[] nodes = new SimpleTreeNode[] {
				new LocalTreeNode()
		};
		return new SimpleTreeModel(
				new SimpleTreeNode("ROOT", Arrays.asList(nodes)));
	}

}
