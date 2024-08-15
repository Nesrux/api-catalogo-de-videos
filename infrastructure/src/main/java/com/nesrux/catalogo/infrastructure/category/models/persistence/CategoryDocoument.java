package com.nesrux.catalogo.infrastructure.category.models.persistence;

import com.nesrux.catalogo.domain.category.Category;
import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

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
         getId(),
         getName(),
         getDescription(),
         isActive(),
         getCreatedAt(),
         getUpdatedAt(),
         getDeletedAt()
      );
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public boolean isActive() {
      return this.active;
   }

   public boolean getActive() {
      return this.active;
   }

   public void setActive(boolean active) {
      this.active = active;
   }

   public Instant getCreatedAt() {
      return this.createdAt;
   }

   public void setCreatedAt(Instant createdAt) {
      this.createdAt = createdAt;
   }

   public Instant getUpdatedAt() {
      return this.updatedAt;
   }

   public void setUpdatedAt(Instant updatedAt) {
      this.updatedAt = updatedAt;
   }

   public Instant getDeletedAt() {
      return this.deletedAt;
   }

   public void setDeletedAt(Instant deletedAt) {
      this.deletedAt = deletedAt;
   }
}
