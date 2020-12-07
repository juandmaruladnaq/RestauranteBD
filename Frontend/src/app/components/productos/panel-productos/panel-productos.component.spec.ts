import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelProductosComponent } from './panel-productos.component';

describe('PanelProductosComponent', () => {
  let component: PanelProductosComponent;
  let fixture: ComponentFixture<PanelProductosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PanelProductosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PanelProductosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
