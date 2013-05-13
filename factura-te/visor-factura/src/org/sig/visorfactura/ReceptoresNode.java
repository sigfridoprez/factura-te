package org.sig.visorfactura;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.actions.NewAction;
import org.openide.actions.PropertiesAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.Lookups;
import org.sig.derbyclient.dto.ReceptoresDto;
import org.sig.editorfactura.NuevaFacturaTopComponent;

public class ReceptoresNode extends AbstractNode {

    public ReceptoresNode(ReceptoresDto dto) {
        super(Children.create(new FacturasChildFactory(dto.getFacturas()), true), Lookups.singleton(dto));
        setDisplayName(dto.getNombre());
    }
    
      @Override
    public Action[] getActions(boolean context) {
        Action[] result = new Action[]{
            new RefreshAction(), SystemAction.get(NewAction.class),
            SystemAction.get(PropertiesAction.class)
        };
        return result;
    }

    private final class RefreshAction extends AbstractAction {

        public RefreshAction() {
            putValue(Action.NAME, "Refresh");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            NuevaFacturaTopComponent d = new NuevaFacturaTopComponent();
            d.setVisible(true);
            VisorFacturaTopComponent.refreshNode();
        }
    }
}
