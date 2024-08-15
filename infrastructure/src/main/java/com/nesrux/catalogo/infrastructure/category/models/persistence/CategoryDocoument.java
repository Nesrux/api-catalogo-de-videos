package com.nesrux.catalogo.infrastructure.category.models.persistence;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

import com.nesrux.catalogo.domain.category.Category;

@Document(indexName = "categories")
public class CategoryDocoument {

   @Id
   private String id;

   @MultiField(
      mainField = @Field(type = FieldType.Text, name = "name"),
      otherFields = @InnerField(suffix = ".keyword", type = FieldType.Keyword)
   )
   private String name;

   @Field(type = FieldType.Text, name = "description")
   private String description;

   @Field(type = FieldType.Boolean, name = "active")
   private boolean active;

   @Field(type = FieldType.Date, name = "created_at")
   private Instant createdAt;

   @Field(type = FieldType.Date, name = "updated_at")
   private Instant updatedAt;

   @Field(type = FieldType.Date, name = "deleted_at")
   private Instant deletedAt;

   public CategoryDocoument(
      final String id,
      final String name,
      final String description,
      final boolean active,
      final Instant createdAt,
      final Instant updatedAt,
      final Instant deletedAt
   ) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.active = active;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
      this.deletedAt = deletedAt;
   }

   public static CategoryDocoument from(final Category aCategory) {
      return new CategoryDocoument(
         aCategory.id(),
         aCategory.name(),
         aCategory.description(),
         aCategory.active(),
         aCategory.createdAt(),
         aCategory.updatedAt(),
         aCategory.deletedAt()
      );
   }

   public Category toCategory() {
      return Category.with(
         id(),
         name(),
         description(),
         isActive(),
         createdAt(),
         updatedAt(),
         deletedAt()
      );
   }

   public String id() {
      return this.id;
   }

   public CategoryDocoument id(String id) {
      this.id = id;
      return this;
   }

   public String name() {
      return this.name;
   }

   public CategoryDocoument name(String name) {
      this.name = name;
      return this;
   }

   public String description() {
      return this.description;
   }

   public CategoryDocoument description(String description) {
      this.description = description;
      return this;
   }

   public boolean isActive() {
      return this.active;
   }

   public CategoryDocoument active(boolean active) {
      this.active = active;
      return this;
   }

   public Instant createdAt() {
      return this.createdAt;
   }

   public CategoryDocoument createdAt(Instant createdAt) {
      this.createdAt = createdAt;
      return this;
   }

   public Instant updatedAt() {
      return this.updatedAt;
   }

   public CategoryDocoument updatedAt(Instant updatedAt) {
      this.updatedAt = updatedAt;
      return this;
   }

   public Instant deletedAt() {
      return this.deletedAt;
   }

   public CategoryDocoument deletedAt(Instant deletedAt) {
      this.deletedAt = deletedAt;
      return this;
   }
}
