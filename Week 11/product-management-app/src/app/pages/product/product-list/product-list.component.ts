import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AgGridAngular } from '@ag-grid-community/angular';
import { ClientSideRowModelModule } from '@ag-grid-community/client-side-row-model';
import { ColDef, ModuleRegistry } from '@ag-grid-community/core';
import { ProductService } from '../../../services/product.service';

import '@ag-grid-community/styles/ag-grid.css';
import '@ag-grid-community/styles/ag-theme-alpine.css';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, AgGridAngular],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.scss'
})
export class ProductListComponent implements OnInit {
  themeClass =  'ag-theme-alpine';

  colDefs: ColDef[] = [
    { field: 'id', sortable: true, filter: true },
    { field: 'name', sortable: true, filter: 'agTextColumnFilter' },
    { field: 'price', sortable: true, filter: 'agNumberColumnFilter' },
    { field: 'status', sortable: true, filter: true },
    {
      headerName: 'Actions',
      cellRenderer: (params: any) => {
        return `
          <button class="btn-edit" data-action="edit">Edit</button>
          <button class="btn-toggle" data-action="toggle">
            ${params.data.status === 'active' ? 'Deactivate' : 'Activate'}
          </button>
        `;
      }
    }
  ];

  defaultColDef: ColDef = {
    flex: 1,
    filter: true,
    sortable: true,
    floatingFilter: true
  };

  rowData: any[] = [];

  constructor(private productService: ProductService) {
    ModuleRegistry.registerModules([ClientSideRowModelModule]);
  }

  ngOnInit(): void {
      this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getAll().subscribe(data => {
      this.rowData = data;
    });
  }
  onCellClicked(event: any): void {
    const action = event.event.target.getAttribute('data-action');
    if (action === 'edit') {
      this.editProduct(event.data);
    } else if (action === 'toggle') {
      this.toggleProductStatus(event.data);
    }
  }

  editProduct(product: any): void {
    alert(`Editing product: ${product.name}`);
  }

  toggleProductStatus(product: any): void {
    const updatedProduct = { ...product, status: product.status === 'active' ? 'inactive' : 'active' };
    this.productService.update(product.id, updatedProduct).subscribe(() => {
      this.loadProducts();
    });
  }
}
