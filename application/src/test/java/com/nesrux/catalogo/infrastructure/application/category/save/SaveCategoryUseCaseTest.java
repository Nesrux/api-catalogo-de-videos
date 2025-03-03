package com.nesrux.catalogo.infrastructure.application.category.save;

import com.nesrux.catalogo.infrastructure.application.UseCaseTest;
import com.nesrux.catalogo.infrastructure.category.save.SaveCategoryUseCase;
import com.nesrux.catalogo.domain.Fixture;
import com.nesrux.catalogo.domain.category.Category;
import com.nesrux.catalogo.domain.category.CategoryGateway;
import com.nesrux.catalogo.domain.exceptions.DomainException;
import com.nesrux.catalogo.domain.utils.InstantUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.UUID;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SaveCategoryUseCaseTest extends UseCaseTest {

    @InjectMocks
    private SaveCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;


    @Test
    public void givenValidCategory_whenCallSave_shouldPersistIt() {
        //given
        final var aCategory = Fixture.Categories.aulas();
        when(categoryGateway.save(any()))
                .thenAnswer(returnsFirstArg());
        //when
        this.useCase.execute(aCategory);

        //then
        verify(categoryGateway, times(1)).save(eq(aCategory));
    }

    @Test
    public void givenInvalidName_whenCallSave_shouldReturnError() {
        //given
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedErrorCount = 1;
        final var aCategory =
                Category.with(
                        UUID.randomUUID().toString().replace("-", ""),
                        "",
                        "Conteudo gravado",
                        true,
                        InstantUtils.now(),
                        InstantUtils.now(),
                        null
                );

        //when
        final var actualError = Assertions.assertThrows(DomainException.class,
                () -> this.useCase.execute(aCategory));

        //then
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());

        verify(categoryGateway, times(0)).save(eq(aCategory));
    }

    @Test
    public void givenInvalidID_whenCallSave_shouldReturnError() {
        //given
        final var expectedErrorMessage = "'id' should not be empty";
        final var expectedErrorCount = 1;
        final var aCategory =
                Category.with(
                        "",
                        "Terror",
                        "Conteudo gravado",
                        true,
                        InstantUtils.now(),
                        InstantUtils.now(),
                        null
                );

        //when
        final var actualError = Assertions.assertThrows(DomainException.class,
                () -> this.useCase.execute(aCategory));

        //then
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());

        verify(categoryGateway, times(0)).save(eq(aCategory));
    }

    @Test
    public void givenNullCategory_whenCallSave_shouldReturnError() {
        //given
        final var expectedErrorMessage = "'aCategory' cannot be null'";
        final var expectedErrorCount = 1;
        final Category aCategory = null;
        //when
        final var actualError = Assertions.assertThrows(DomainException.class,
                () -> this.useCase.execute(aCategory));

        //then
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());

        verify(categoryGateway, times(0)).save(eq(aCategory));
    }
}
