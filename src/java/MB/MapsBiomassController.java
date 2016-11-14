package MB;

import Entidad.MapsBiomass;
import DAO.util.JsfUtil;
import DAO.util.PaginationHelper;
import DataAccessObject.MapsBiomassFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "mapsBiomassController")
@SessionScoped
public class MapsBiomassController implements Serializable {

    private MapsBiomass current;
    private DataModel items = null;
    @EJB
    private DataAccessObject.MapsBiomassFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public MapsBiomassController() {
    }

    public MapsBiomass getSelected() {
        if (current == null) {
            current = new MapsBiomass();
            current.setMapsBiomassPK(new Entidad.MapsBiomassPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private MapsBiomassFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (MapsBiomass) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new MapsBiomass();
        current.setMapsBiomassPK(new Entidad.MapsBiomassPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getMapsBiomassPK().setGrid450Id(current.getGrid450().getGrid450Id());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MapsBiomassCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (MapsBiomass) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getMapsBiomassPK().setGrid450Id(current.getGrid450().getGrid450Id());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MapsBiomassUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (MapsBiomass) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MapsBiomassDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = MapsBiomass.class)
    public static class MapsBiomassControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MapsBiomassController controller = (MapsBiomassController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mapsBiomassController");
            return controller.ejbFacade.find(getKey(value));
        }

        Entidad.MapsBiomassPK getKey(String value) {
            Entidad.MapsBiomassPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entidad.MapsBiomassPK();
            key.setGrid450Id(Integer.parseInt(values[0]));
            key.setTagTime(values[1]);
            return key;
        }

        String getStringKey(Entidad.MapsBiomassPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getGrid450Id());
            sb.append(SEPARATOR);
            sb.append(value.getTagTime());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MapsBiomass) {
                MapsBiomass o = (MapsBiomass) object;
                return getStringKey(o.getMapsBiomassPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MapsBiomass.class.getName());
            }
        }

    }

}
