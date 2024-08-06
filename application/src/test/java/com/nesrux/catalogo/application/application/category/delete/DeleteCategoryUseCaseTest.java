package com.nesrux.catalogo.application.application.category.delete;

import com.nesrux.catalogo.domain.Fixture;
import com.nesrux.catalogo.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class DeleteCategoryUseCaseTest {
    @InjectMocks
    private DeleteCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;


    public void givenValidId_whenCallsDelete_shouldBeOk() {
        //given
        final var aulas = Fixture.Categories.aulas();
        final var expectedId = aulas.id();

        doNothing()
                .when(this.categoryGateway).deleteById(anyString());

        //when
        Assertions.assertDoesNotThrow(() -> this.categoryGateway.deleteById(expectedId));

        //then
        verify(this.categoryGateway, times(1))
                .deleteById(expectedId);
    }

    public void givenInvalidId_whenCallsDelete_shouldBeOk() {
        //given
        final String expectedId = null;

        //when
        Assertions.assertDoesNotThrow(() -> this.categoryGateway.deleteById(expectedId));

        //then
        verify(this.categoryGateway, never())
                .deleteById(expectedId);
    }
}
