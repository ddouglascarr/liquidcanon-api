package org.ddouglascarr.unit;

import org.ddouglascarr.controllers.UnitsController;
import org.ddouglascarr.enums.ExceptionCodes;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Unit;
import org.ddouglascarr.services.UnitService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UnitsControllerTests
{
    @Mock
    private UnitService unitService;

    @InjectMocks
    private UnitsController unitsController = new UnitsController();

    private Unit mockUnit2;
    private Unit mockUnit3;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        mockUnit2 = new Unit();
        mockUnit2.setId(new Long(2));
        mockUnit2.setName("Mock Unit 2");

        mockUnit3 = new Unit();
        mockUnit3.setId(new Long(3));
        mockUnit3.setName("Mock Unit 3");
    }

    @Test
    public void getUnitShouldReturn404IfNotFound() throws ItemNotFoundException
    {
        when(unitService.findOne(new Long(6))).thenThrow(new ItemNotFoundException());
        ResponseEntity<Unit> resp = unitsController.getUnit(new Long(6));
        assertEquals(resp.getStatusCode(), HttpStatus.NOT_FOUND);
        MultiValueMap<String, String> headers = resp.getHeaders();
        assertEquals(headers.getFirst("error-code"), ExceptionCodes.ITEM_NOT_FOUND.toString());
    }

    @Test
    public void getUnitShouldReturnUnit() throws ItemNotFoundException
    {
        when(unitService.findOne(new Long(2))).thenReturn(mockUnit2);
        ResponseEntity<Unit> resp = unitsController.getUnit(new Long(2));
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
        Unit returnedUnit = resp.getBody();
        assertEquals(returnedUnit, mockUnit2);
    }
}
