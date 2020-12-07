import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelPedidosComponent } from './panel-pedidos.component';

describe('PanelPedidosComponent', () => {
  let component: PanelPedidosComponent;
  let fixture: ComponentFixture<PanelPedidosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PanelPedidosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PanelPedidosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
