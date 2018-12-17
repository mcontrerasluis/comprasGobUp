import { element, by, ElementFinder } from 'protractor';

export class FacturaComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-factura div table .btn-danger'));
    title = element.all(by.css('jhi-factura div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class FacturaUpdatePage {
    pageTitle = element(by.id('jhi-factura-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    fechaInput = element(by.id('field_fecha'));
    detallesInput = element(by.id('field_detalles'));
    estatusSelect = element(by.id('field_estatus'));
    metodoPagoSelect = element(by.id('field_metodoPago'));
    fechaPagoInput = element(by.id('field_fechaPago'));
    montoPagadoInput = element(by.id('field_montoPagado'));
    ordenCompraSelect = element(by.id('field_ordenCompra'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setFechaInput(fecha) {
        await this.fechaInput.sendKeys(fecha);
    }

    async getFechaInput() {
        return this.fechaInput.getAttribute('value');
    }

    async setDetallesInput(detalles) {
        await this.detallesInput.sendKeys(detalles);
    }

    async getDetallesInput() {
        return this.detallesInput.getAttribute('value');
    }

    async setEstatusSelect(estatus) {
        await this.estatusSelect.sendKeys(estatus);
    }

    async getEstatusSelect() {
        return this.estatusSelect.element(by.css('option:checked')).getText();
    }

    async estatusSelectLastOption() {
        await this.estatusSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setMetodoPagoSelect(metodoPago) {
        await this.metodoPagoSelect.sendKeys(metodoPago);
    }

    async getMetodoPagoSelect() {
        return this.metodoPagoSelect.element(by.css('option:checked')).getText();
    }

    async metodoPagoSelectLastOption() {
        await this.metodoPagoSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setFechaPagoInput(fechaPago) {
        await this.fechaPagoInput.sendKeys(fechaPago);
    }

    async getFechaPagoInput() {
        return this.fechaPagoInput.getAttribute('value');
    }

    async setMontoPagadoInput(montoPagado) {
        await this.montoPagadoInput.sendKeys(montoPagado);
    }

    async getMontoPagadoInput() {
        return this.montoPagadoInput.getAttribute('value');
    }

    async ordenCompraSelectLastOption() {
        await this.ordenCompraSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async ordenCompraSelectOption(option) {
        await this.ordenCompraSelect.sendKeys(option);
    }

    getOrdenCompraSelect(): ElementFinder {
        return this.ordenCompraSelect;
    }

    async getOrdenCompraSelectedOption() {
        return this.ordenCompraSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class FacturaDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-factura-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-factura'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
