/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sig.visorfactura;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.sig.derbyclient.dto.ReceptoresDto;

/**
 *
 * @author Edlim
 */
public class FacturaTRootNode extends AbstractNode {

    public FacturaTRootNode(List<ReceptoresDto> resultList) {
        super(Children.create(new ReceptoresChildFactory(resultList), true));
        setDisplayName("Root");
    }
    
     @Override
    public Action[] getActions(boolean context) {
        Action[] result = new Action[]{
            new RefreshAction()
        };
        return result;
    }

    private final class RefreshAction extends AbstractAction {

        public RefreshAction() {
            putValue(Action.NAME, "Refresh");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            VisorFacturaTopComponent.refreshNode();
        }
    }
}
