package com.sergiofah.integracao.controller;

import com.sergiofah.integracao.model.CategoryDTO;
import com.sergiofah.integracao.model.LineDTO;
import com.sergiofah.integracao.model.ProductDTO;
import com.sergiofah.integracao.service.CategoryService;
import com.sergiofah.integracao.service.LineService;
import com.sergiofah.integracao.service.ProductService;
import com.sergiofah.integracao.service.Service;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import static org.springframework.test.util.AssertionErrors.assertEquals;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(ApplicationExtension.class)
class ProductPageControllerTest {

    @Mock
    private Service service;

    @Mock
    private LineService lineService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private ProductService productService;

    @Spy
    @InjectMocks
    private ProductPageController productPageController;

    @Test
    public void initializeServerStatusTest1() {
        Mockito.doNothing().when(productPageController).populateComboBox();
        Mockito.when(service.getServerStatus()).thenReturn(true);
        Mockito.when(lineService.getLines()).thenReturn(new ArrayList<LineDTO>());

        productPageController.initialize();

        verify(productPageController, times(1)).populateComboBox();
    }

    @Test
    public void initializeServerStatusTest2() {
        Mockito.when(service.getServerStatus()).thenReturn(false);
        productPageController.serverStatus = new Label();

        productPageController.initialize();

        Assertions.assertThat(productPageController.serverStatus).hasText("Status: Server Offline");
    }

    @Test
    public void populateComboBoxTest1() {
        productPageController.linesComboBox = new ComboBox<>();
        LineDTO lineDTO = new LineDTO(1L, "line1");
        LineDTO lineDTO2 = new LineDTO(2L, "line2");
        List<LineDTO> lineDTOList = new ArrayList<>();
        lineDTOList.add(lineDTO);
        lineDTOList.add(lineDTO2);
        productPageController.lineDTOList = lineDTOList;

        productPageController.populateComboBox();

        assertEquals("Checking if the ComboBox's values were set correctly", FXCollections.observableArrayList(lineDTOList.stream().map(LineDTO::getLine).collect(Collectors.toList())), productPageController.linesComboBox.getItems());
    }

    @Test
    public void onClickComboBoxTest1() {
        productPageController.linesComboBox = new ComboBox<String>();
        List<LineDTO> lineDTOList = new ArrayList<>();
        lineDTOList.add(new LineDTO(1L, "line1"));
        lineDTOList.add(new LineDTO(2L, "line2"));
        productPageController.lineDTOList = lineDTOList;
        productPageController.modelsTitledPane = new TitledPane();
        Mockito.doNothing().when(productPageController).populateTreeView();

        productPageController.onClickComboBox("line1");

        verify(productPageController, times(1)).populateTreeView();
        assertFalse(productPageController.modelsTitledPane.isDisable());
        assertTrue(productPageController.modelsTitledPane.isExpanded());
    }

    @Test
    public void populateTreeViewTest1() {
        productPageController.selectedLineId = 1L;
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        categoryDTOList.add(new CategoryDTO(1L, new LineDTO(1L, "line1"), "cat1"));
        categoryDTOList.add(new CategoryDTO(2L, new LineDTO(1L, "line1"), "cat2"));
        List<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList.add(new ProductDTO(1L, new LineDTO(), categoryDTOList.get(0), "model1", "desc", "url"));
        productDTOList.add(new ProductDTO(2L, new LineDTO(), categoryDTOList.get(1), "model2", "desc", "url"));
        productPageController.modelsTreeView = new TreeView<>();

        when(categoryService.getCategoriesFromLineId(Mockito.anyLong())).thenReturn(categoryDTOList);
        when(productService.getProductsFromCategoryId(Mockito.anyLong())).thenReturn(productDTOList);
        productPageController.populateTreeView();;

        assertFalse(productPageController.modelsTreeView.isShowRoot());
        Assertions.assertThat(productPageController.modelsTreeView.getTreeItem(0).getChildren().toString()).isEqualTo("[TreeItem [ value: model1 ], TreeItem [ value: model2 ]]");
        Assertions.assertThat(productPageController.modelsTreeView.getTreeItem(1).getChildren().toString()).isEqualTo("[TreeItem [ value: model1 ], TreeItem [ value: model2 ]]");
    }

    @Test
    public void selectionTreeViewHandlerTest1() {
        productPageController.modelsTreeView = new TreeView<>();
        List<ProductDTO> productDTOList = new ArrayList<>();
        ProductDTO productDTO = new ProductDTO(1L, new LineDTO(), new CategoryDTO(), "model1", "desc", "url");
        productDTOList.add(productDTO);
        TreeItem<String> productItem = new TreeItem<>(productDTO.getModel());
        TreeItem<String> rootItem = new TreeItem<>();
        TreeItem<String> categoryItem = new TreeItem<>();
        rootItem.getChildren().add(new TreeItem<>("cat1"));
        categoryItem.getChildren().add(productItem);
        productPageController.modelsTreeView.setRoot(rootItem);
        productPageController.productDTOList = productDTOList;

        when(productService.getProductFromId(Mockito.anyLong())).thenReturn(productDTO);
        Mockito.doNothing().when(productPageController).populateModelDetails(productDTO);
        productPageController.selectionTreeViewHandler(productItem);

        verify(productPageController, times(1)).populateModelDetails(productDTO);
    }
    @Test
    public void populateModelDetailsTest1() throws InterruptedException {
        ProductDTO productDTO = new ProductDTO(1L, new LineDTO(), new CategoryDTO(), "model1", "desc", "https://www.google.com/images/branding/googlelogo/2x/googlelogo_light_color_92x30dp.png");
        Image image = new Image("https://www.google.com/images/branding/googlelogo/2x/googlelogo_light_color_92x30dp.png");
        productPageController.productNameLabel = new Label();
        productPageController.productDescLabel = new Label();
        productPageController.productImageView = new ImageView();
        productPageController.modelDetailsAnchorPane = new AnchorPane();

        productPageController.populateModelDetails(productDTO);
        productPageController.loadImage.join();

        assertEquals("Checking if the width of the image is the same as the loaded one", image.getWidth(), productPageController.productImageView.getImage().getWidth());
        assertEquals("Checking if the height of the image is the same as the loaded one", image.getHeight(), productPageController.productImageView.getImage().getHeight());
        assertEquals("Checking if text on product label was set correctly", productDTO.getModel(), productPageController.productNameLabel.getText());
        assertEquals("Checking if text on description was set correctly", productDTO.getDescription(), productPageController.productDescLabel.getText());
        assertTrue(productPageController.modelDetailsAnchorPane.isVisible());
    }
}