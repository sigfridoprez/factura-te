/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sig.registrousuario;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;

@OptionsPanelController.SubRegistration(
    location = "General2",
    displayName = "#General2Option_DisplayName_DatosEmisor",
    keywords = "#General2Option_Keywords_DatosEmisor",
    keywordsCategory = "General2/DatosEmisor")
@org.openide.util.NbBundle.Messages({"General2Option_DisplayName_DatosEmisor=Datos Emisor",
    "General2Option_Keywords_DatosEmisor=Datos Generales del Emisor"})
public final class DatosEmisorOptionsPanelController extends OptionsPanelController {

    private DatosEmisorPanel panel;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private boolean changed;

    public void update() {
        getPanel().load();
        changed = false;
    }

    public void applyChanges() {
        getPanel().store();
        changed = false;
    }

    public void cancel() {
        // need not do anything special, if no changes have been persisted yet
    }

    public boolean isValid() {
        return getPanel().valid();
    }

    public boolean isChanged() {
        return changed;
    }

    public HelpCtx getHelpCtx() {
        return null; // new HelpCtx("...ID") if you have a help set
    }

    public JComponent getComponent(Lookup masterLookup) {
        return getPanel();
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    private DatosEmisorPanel getPanel() {
        if (panel == null) {
            panel = new DatosEmisorPanel(this);
        }
        return panel;
    }

    void changed() {
        if (!changed) {
            changed = true;
            pcs.firePropertyChange(OptionsPanelController.PROP_CHANGED, false, true);
        }
        pcs.firePropertyChange(OptionsPanelController.PROP_VALID, null, null);
    }
}
