package org.sig.visorfactura;

import java.awt.Image;
import org.openide.ErrorManager;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.Lookups;
import org.sig.derbyclient.dto.FacturaDto;

public class FacturaNode extends AbstractNode {

    public FacturaNode(FacturaDto c) {
        super(Children.LEAF, Lookups.singleton(c));
         setDisplayName("FOLIO: " + c.getFolioFactura());
        setShortDescription("ShortDescription " + c.getFolioFactura() + c.getNumFactura());
    }

    @Override
    public Image getIcon(int i) {
        return ImageUtilities.loadImage("/org/sig/visorfactura/new_icon.png");
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();
        FacturaDto obj = getLookup().lookup(FacturaDto.class);
        try {
            Property indexProp = new PropertySupport.Reflection(obj, Integer.class, "getIdFactura", null);
            Property dateProp = new PropertySupport.Reflection(obj, String.class, "getFolioFactura", null);

            indexProp.setName("index");
            dateProp.setName("date");
            set.put(indexProp);
            set.put(dateProp);
        } catch (NoSuchMethodException ex) {
            ErrorManager.getDefault();
        }
        sheet.put(set);
        return sheet;
    }
  
}
