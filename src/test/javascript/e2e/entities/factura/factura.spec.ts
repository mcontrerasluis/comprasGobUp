/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { FacturaComponentsPage, FacturaDeleteDialog, FacturaUpdatePage } from './factura.page-object';

const expect = chai.expect;

describe('Factura e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let facturaUpdatePage: FacturaUpdatePage;
    let facturaComponentsPage: FacturaComponentsPage;
    let facturaDeleteDialog: FacturaDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Facturas', async () => {
        await navBarPage.goToEntity('factura');
        facturaComponentsPage = new FacturaComponentsPage();
        expect(await facturaComponentsPage.getTitle()).to.eq('comprasGobUpApp.factura.home.title');
    });

    it('should load create Factura page', async () => {
        await facturaComponentsPage.clickOnCreateButton();
        facturaUpdatePage = new FacturaUpdatePage();
        expect(await facturaUpdatePage.getPageTitle()).to.eq('comprasGobUpApp.factura.home.createOrEditLabel');
        await facturaUpdatePage.cancel();
    });

    it('should create and save Facturas', async () => {
        const nbButtonsBeforeCreate = await facturaComponentsPage.countDeleteButtons();

        await facturaComponentsPage.clickOnCreateButton();
        await promise.all([
            facturaUpdatePage.setFechaInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            facturaUpdatePage.setDetallesInput('detalles'),
            facturaUpdatePage.estatusSelectLastOption(),
            facturaUpdatePage.metodoPagoSelectLastOption(),
            facturaUpdatePage.setFechaPagoInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            facturaUpdatePage.setMontoPagadoInput('5'),
            facturaUpdatePage.ordenCompraSelectLastOption()
        ]);
        expect(await facturaUpdatePage.getFechaInput()).to.contain('2001-01-01T02:30');
        expect(await facturaUpdatePage.getDetallesInput()).to.eq('detalles');
        expect(await facturaUpdatePage.getFechaPagoInput()).to.contain('2001-01-01T02:30');
        expect(await facturaUpdatePage.getMontoPagadoInput()).to.eq('5');
        await facturaUpdatePage.save();
        expect(await facturaUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await facturaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Factura', async () => {
        const nbButtonsBeforeDelete = await facturaComponentsPage.countDeleteButtons();
        await facturaComponentsPage.clickOnLastDeleteButton();

        facturaDeleteDialog = new FacturaDeleteDialog();
        expect(await facturaDeleteDialog.getDialogTitle()).to.eq('comprasGobUpApp.factura.delete.question');
        await facturaDeleteDialog.clickOnConfirmButton();

        expect(await facturaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
